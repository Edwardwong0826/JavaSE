package com.cool.scopedvalues.user;

/*
 * Ponder this question
 *  - Do we need to make this class Thread safe ?
 *  - Yes, user class must be written in thread safe manner, its method need to be appropriately synchronized so that InheritableThreadLocal can work correctly
 */
public class User {
    
    private String id;
    
    public User(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", super.toString(), this.id);
    }
}
