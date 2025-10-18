package com.task2;

import java.util.*;

public class ProfileService {

    public static Map<Long, Map<Long, List<Profile>>> groupByOrgIdAndGroupId(List<Profile> data) {
        if (data == null || data.isEmpty()) return Collections.emptyMap();

        Map<Long, Map<Long, List<Profile>>> res = new HashMap<>();

        for(Profile p : data){
            if(p == null) continue;

            if(p.getOrgId() == null || p.getGroupId() == null) continue;

            Map<Long, List<Profile>> byGroup = res.computeIfAbsent(p.getOrgId(), k -> new HashMap<>());
            List<Profile> bucket = byGroup.computeIfAbsent(p.getGroupId(), k -> new ArrayList<>());
            bucket.add(p);
        }

        return res;
    }

}
