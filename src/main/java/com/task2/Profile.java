package com.task2;

public class Profile {

    private Long id;
    private Long orgId;
    private Long groupId;

    public Profile(Long id, Long orgId, Long groupId) {
        this.id = id;
        this.orgId = orgId;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public Long getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "{"  + id +
                ", " + orgId +
                ", " + groupId +
                "}";
    }
}
