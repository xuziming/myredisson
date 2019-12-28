/**
 * Copyright (c) 2013-2019 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.api;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * Distributed implementation of {@link java.util.concurrent.ExecutorService}
 * 
 * @author Nikita Koksharov
 *
 */
public interface RExecutorService extends ExecutorService, RExecutorServiceAsync {

    /**
     * MapReduce's executor name 
     */
    String MAPREDUCE_NAME = "redisson_mapreduce";
    
    /**
     * Submits a value-returning task for execution synchronously and returns a
     * Future representing the pending results of the task. The
     * Future's {@code get} method will return the task's result upon
     * successful completion.
     *
     * @param task the task to submit
     * @param <T> the type of the task's result
     * @return a Future representing pending completion of the task
     */
    @Override
    <T> RExecutorFuture<T> submit(Callable<T> task);
    
    /**
     * Submits tasks batch for execution synchronously. 
     * All tasks are stored to executor request queue atomically, 
     * if case of any error none of tasks will be added.
     * 
     * @param tasks - tasks to execute
     * @return Future object
     */
    RExecutorBatchFuture submit(Callable<?>...tasks);
    
    /**
     * Submits a Runnable task for execution and returns a Future
     * representing that task. The Future's {@code get} method will
     * return the given result upon successful completion.
     *
     * @param task the task to submit
     * @param result the result to return
     * @param <T> the type of the result
     * @return a Future representing pending completion of the task
     */
    @Override
    <T> RExecutorFuture<T> submit(Runnable task, T result);;

    /**
     * Submits a Runnable task for execution and returns a Future
     * representing that task. The Future's {@code get} method will
     * return {@code null} upon <em>successful</em> completion.
     *
     * @param task the task to submit
     * @return a Future representing pending completion of the task
     */
    @Override
    RExecutorFuture<?> submit(Runnable task);

    /**
     * Submits tasks batch for execution synchronously. 
     * All tasks are stored to executor request queue atomically, 
     * if case of any error none of tasks will be added.
     * 
     * @param tasks - tasks to execute
     * @return Future object
     */
    RExecutorBatchFuture submit(Runnable...tasks);
    
    /**
     * Returns executor name
     * 
     * @return name of service
     */
    String getName();
    
    /**
     * Deletes executor request queue and state objects
     * 
     * @return <code>true</code> if any of objects were deleted
     */
    boolean delete();

    /*
     * Use {@link #registerWorkers(WorkerOptions)} setting instead
     * 
     */
    @Deprecated
    void registerWorkers(int workers);

    /*
     * Use {@link #registerWorkers(WorkerOptions)} setting instead
     * 
     */
    @Deprecated
    void registerWorkers(int workers, ExecutorService executor);

    /**
     * Register workers
     * 
     * @param options - worker options
     */
    void registerWorkers(WorkerOptions options);

    /**
     * Returns amount of tasks awaiting for execution and/or currently in execution.
     *
     * @return amount of tasks
     */
    int getTaskCount();

    /**
     * Returns active workers amount available for tasks execution.
     * 
     * @return workers amount
     */
    int countActiveWorkers();

    /**
     * Returns <code>true</code> if this Executor Service has task
     * by <code>taskId</code> awaiting for execution and/or currently in execution
     *
     * @param taskId - id of task
     * @return <code>true</code> if this Executor Service has task
     */
    boolean hasTask(String taskId);

    /**
     * Returns list of task ids awaiting for execution and/or currently in execution.
     *
     * @return task ids
     */
    Set<String> getTaskIds();

    /**
     * Cancel task by id
     * 
     * @see RExecutorFuture#getTaskId()
     * 
     * @param taskId - id of task
     * @return <code>true</code> if task has been canceled successfully
     */
    boolean cancelTask(String taskId);
    
    /**
     * Submits tasks batch for execution synchronously. 
     * All tasks are stored to executor request queue atomically, 
     * if case of any error none of tasks will be added.
     * 
     * @param tasks - tasks to execute
     */
    void execute(Runnable...tasks);

}
