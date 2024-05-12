package MachineCoding.Uber;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*

Question: A job is defined as a set of tasks where each task can depend on one or more tasks. 

Write a job class with the following functionality. 
addTask(taskName, executable function, dependencies): user will add task to the job
run(): user will run the job. This will execute all the tasks in the job as per dependencies. 
Run the tasks in parallel wherever possible. 

User will use the job class like 
Job job = new Job()
job.addTask('A', (runnable/callable), [])
job.addTask('B', (runnable/callable), [])
job.addTask('C', (runnable/callable), [‘A’, ‘B’])
job.addTask('D', (runnable/callable), [‘C’, ‘E’])
job.addTask('E’', (runnable/callable), [])
job.run();

A------+
       +
       +
       +--- C ---+
       +         +
       +         +
B----- +         +
                 +----D 
                 +
                 +
E-----------------

ABECD or ABCED


*/

// Main class should be named 'Solution' and should not be public.
class Solution {

    private static Runnable createRunnable(int sleepTime, String name) {
        return () -> {
            System.out.println("Starting " + name + " with sleep time " + sleepTime);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
            System.out.println("Ending " + name + " with sleep time " + sleepTime);
        };
    }

    public static void main(String[] args) {
        System.out.println("Hello, World");
        TaskSelectionStrategy selectionStrategy = new OrderedTaskSelectionStrategy();
        OrderedJobExecutor jobExecutor = new OrderedJobExecutor(selectionStrategy);
        jobExecutor.addTask("A", createRunnable(2, "A"), null);
        jobExecutor.addTask("B", createRunnable(3, "B"), null);
        jobExecutor.addTask("E", createRunnable(3, "E"), null);
        jobExecutor.addTask("C", createRunnable(3, "C"), Arrays.asList(new String[] { "A", "B" }));
        jobExecutor.addTask("D", createRunnable(3, "D"), Arrays.asList(new String[] { "C", "E" }));

        jobExecutor.run();
    }

}

interface TaskSelectionStrategy {
    public void addTask(String taskName);

    public List<String> getNextExecutableTasks();

    public void markComplete(String taskName);

    public void addTaskDependency(String name, List<String> dependencies);
}

class OrderedTaskSelectionStrategy implements TaskSelectionStrategy {
    Map<String, List<String>> dependenciesMap;
    Set<String> visited;

    public OrderedTaskSelectionStrategy() {
        this.visited = new HashSet<>();
        this.dependenciesMap = new ConcurrentHashMap<>();
    }

    public void addTask(String taskName) {
        this.dependenciesMap.putIfAbsent(taskName, new ArrayList<>());
    }

    public void addTaskDependency(String name, List<String> dependencies) {
        for (String dependency : dependencies) {
            this.dependenciesMap.putIfAbsent(name, new ArrayList<>());
            List<String> currJobDependencies = this.dependenciesMap.get(name);

            if (!currJobDependencies.contains(dependency)) {
                currJobDependencies.add(dependency);
            }
        }
    }

    public List<String> getNextExecutableTasks() {
        List<String> result = new ArrayList<>();
        for (String task : this.dependenciesMap.keySet()) {
            if (!this.visited.contains(task) && this.dependenciesMap.get(task).size() == 0) {
                result.add(task);
                this.visited.add(task);
            }
        }
        return result;
    }

    public void markComplete(String taskName) {
        for (String task : this.dependenciesMap.keySet()) {
            if (this.dependenciesMap.get(task).contains(taskName)) {
                this.dependenciesMap.get(task).remove(taskName);
            }
        }
    }
}

class OrderedJobExecutor implements Runnable {
    Map<String, Job> jobMap;

    TaskSelectionStrategy selectionStrategy;

    public OrderedJobExecutor(TaskSelectionStrategy selectionStrategy) {
        this.jobMap = new ConcurrentHashMap<>();
        this.selectionStrategy = selectionStrategy;
    }

    void addTask(String taskName, Runnable task, List<String> dependencies) {
        Job newJob = new Job(taskName, task);
        this.jobMap.put(taskName, newJob);
        this.selectionStrategy.addTask(taskName);

        if (dependencies != null)
            this.selectionStrategy.addTaskDependency(taskName, dependencies);
    }

    public void run() {
        // initialize a executor service with total task cardinality
        int totalTasks = this.jobMap.keySet().size();
        ExecutorService executorService = Executors.newFixedThreadPool(totalTasks);

        Queue<Object[]> currentJobs = new ArrayDeque<>();

        List<String> initialTasks = this.selectionStrategy.getNextExecutableTasks();
        for (String taskName : initialTasks) {
            Job j = this.jobMap.get(taskName);
            Future<?> f = executorService.submit(j.task);

            currentJobs.add(new Object[] { f, taskName });
        }

        while (!currentJobs.isEmpty()) {
            Object[] curr = currentJobs.poll();
            String currTaskName = (String) curr[1];

            Future<?> currTask = (Future<?>) curr[0];
            if (currTask.isDone()) {
                this.selectionStrategy.markComplete(currTaskName);
                List<String> nextTasks = this.selectionStrategy.getNextExecutableTasks();
                System.out.printf("Next tasks enabled %s", nextTasks.toString());
                for (String taskName : nextTasks) {
                    Job j = this.jobMap.get(taskName);
                    Future<?> f = executorService.submit(j.task);

                    currentJobs.add(new Object[] { f, taskName });
                }
            } else {
                currentJobs.add(curr);
            }
        }

        // initialize - fetch the tasks with indegree as 0
        // add it to the queue - queue contains only parallel tasks running

        // keep iterating while queue is empty
        // taskStatus = check the status of each task
        // if taskStatus == complete
        // reduce the indegree of the dependent task
        // if dependent task indegree is 0
        // add it to the queue
        // else
        // add it back to the queue
    }
}

class Job {
    String name;
    Runnable task;

    public Job(String name, Runnable task) {
        this.name = name;
        this.task = task;
    }
}