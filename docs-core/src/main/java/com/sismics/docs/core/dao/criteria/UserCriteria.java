package com.sismics.docs.core.dao.criteria;

/**
 * User criteria.
 *
 * @author bgamard 
 */
public class UserCriteria {
    /**
     * Search query.
     */
    private String search;
    
    /**
     * Group ID.
     */
    private String groupId;

    /**
     * User ID.
     */
    private String userId;

    /**
     * Username.
     */
    private String userName;
    
    /**
     * Only disabled users.
     */
    private Boolean onlyDisabled;

    public String getSearch() {
        return search;
    }

    public UserCriteria setSearch(String search) {
        this.search = search;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public UserCriteria setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserCriteria setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserCriteria setUserName(String userName) {
        this.userName = userName;
        return this;
    }
    
    public Boolean getOnlyDisabled() {
        return onlyDisabled;
    }
    
    public UserCriteria setOnlyDisabled(Boolean onlyDisabled) {
        this.onlyDisabled = onlyDisabled;
        return this;
    }
}
