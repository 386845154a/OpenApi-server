package com.github.hollykunge.openapi.hystrix;

/**
 * @author tanghc
 */
public enum ExecutionIsolationStrategy {
    /** 未指定 */
    NONE,
    THREAD,
    SEMAPHORE;

    private ExecutionIsolationStrategy() {
    }
}
