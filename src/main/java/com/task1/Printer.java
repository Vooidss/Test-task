package com.task1;

import com.task2.Profile;

import java.util.List;
import java.util.Map;

public class Printer {
    public static String prettyPrintJson(Map<Long, Map<Long, List<Profile>>> map) {
        if (map == null || map.isEmpty()) return "{\n}";
        String tab = "  ";
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        List<Long> orgKeys = map.keySet().stream().sorted().toList();

        for (int i = 0; i < orgKeys.size(); i++) {
            Long orgId = orgKeys.get(i);
            sb.append(tab).append("\"").append(orgId).append("\": ").append("{\n");

            Map<Long, List<Profile>> inner = map.get(orgId);
            List<Long> grpKeys = inner.keySet().stream().sorted().toList();

            for (int j = 0; j < grpKeys.size(); j++) {
                Long groupId = grpKeys.get(j);
                sb.append(tab).append(tab).append("\"").append(groupId).append("\": ").append("[");

                List<Profile> profiles = inner.get(groupId);
                for (Profile p : profiles) {
                    sb.append(p);
                }
                sb.append("]");

                if (j < grpKeys.size() - 1) sb.append(",");
                sb.append("\n");
            }

            sb.append(tab).append("}");
            if (i < orgKeys.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

}
