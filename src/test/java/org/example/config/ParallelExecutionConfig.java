package org.example.config;

/**
 * Parallel Execution Configuration
 * Manages thread group settings and cross-browser execution parameters
 */
public class ParallelExecutionConfig {

    // Thread Configuration
    public static final int THREAD_COUNT = 4;
    public static final int FORK_COUNT = 1;
    public static final String PARALLEL_MODE = "methods";
    public static final long THREAD_TIMEOUT = 300000; // 5 minutes

    // Browser Configuration
    public enum Browser {
        CHROME("chrome"),
        FIREFOX("firefox"),
        EDGE("edge");

        private String name;

        Browser(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    // Parallel Execution Groups
    public enum ThreadGroup {
        GROUP_1("Group_1"),
        GROUP_2("Group_2"),
        GROUP_3("Group_3"),
        GROUP_4("Group_4");

        private String groupName;

        ThreadGroup(String groupName) {
            this.groupName = groupName;
        }

        public String getGroupName() {
            return groupName;
        }
    }

    /**
     * Get current thread ID
     * @return Thread ID
     */
    public static long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    /**
     * Get current thread name
     * @return Thread name
     */
    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    /**
     * Get thread group for current thread
     * @return Thread group
     */
    public static ThreadGroup getThreadGroup() {
        long threadId = getCurrentThreadId();
        int threadIndex = (int) (threadId % THREAD_COUNT);
        return ThreadGroup.values()[threadIndex];
    }

    /**
     * Get browser for current thread based on thread index
     * Distributes browsers across threads for parallel execution
     *
     * @return Browser type
     */
    public static Browser getBrowserForThread() {
        long threadId = getCurrentThreadId();
        int browserIndex = (int) (threadId % Browser.values().length);
        return Browser.values()[browserIndex];
    }

    /**
     * Log thread and browser information
     */
    public static void logThreadInfo() {
        System.out.println(
            "=========================================" +
            "\nThread ID: " + getCurrentThreadId() +
            "\nThread Name: " + getCurrentThreadName() +
            "\nThread Group: " + getThreadGroup().getGroupName() +
            "\nAssigned Browser: " + getBrowserForThread().getName() +
            "\n========================================="
        );
    }

    /**
     * Get system property or default value
     *
     * @param property Property name
     * @param defaultValue Default value
     * @return Property value or default
     */
    public static String getProperty(String property, String defaultValue) {
        return System.getProperty(property, defaultValue);
    }

    /**
     * Get browser from system property
     *
     * @return Browser type
     */
    public static String getBrowserFromProperty() {
        return getProperty("browser", "chrome");
    }

    /**
     * Get thread count from system property
     *
     * @return Thread count
     */
    public static int getThreadCountFromProperty() {
        try {
            return Integer.parseInt(getProperty("threadCount", String.valueOf(THREAD_COUNT)));
        } catch (NumberFormatException e) {
            return THREAD_COUNT;
        }
    }
}

