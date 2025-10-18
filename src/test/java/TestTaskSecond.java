import com.task1.Info;
import com.task2.Profile;
import com.task2.ProfileService;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TestTaskSecond {

    @Test
    public void baseGrouping() {

        List<Profile> input = List.of(
                new Profile(1L, 0L, 1L),
                new Profile(2L, 0L, 1L),
                new Profile(3L, 0L, 2L),
                new Profile(4L, 1L, 1L),
                new Profile(5L, 1L, 2L)
        );


        Map<Long, Map<Long, List<Profile>>> res = ProfileService.groupByOrgIdAndGroupId(input);

        assertEquals(2, res.size());
        assertTrue(res.containsKey(0L));
        assertTrue(res.containsKey(1L));

        Map<Long, List<Profile>> g0 = res.get(0L);
        assertEquals(2, g0.size());
        assertTrue(g0.containsKey(1L));
        assertTrue(g0.containsKey(2L));
        assertEquals(Set.of(1L, 2L), toIdSet(g0.get(1L)));
        assertEquals(Set.of(3L), toIdSet(g0.get(2L)));

        Map<Long, List<Profile>> g1 = res.get(1L);
        assertEquals(2, g1.size());
        assertTrue(g1.containsKey(1L));
        assertTrue(g1.containsKey(2L));
        assertEquals(Set.of(4L), toIdSet(g1.get(1L)));
        assertEquals(Set.of(5L), toIdSet(g1.get(2L)));
    }

    @Test
    public void ignoresNullsAndIncompleteProfiles() {

        List<Profile> input = Arrays.asList(
                new Profile(1L, 0L, 1L),
                null,
                new Profile(2L, null, 1L),
                new Profile(3L, 0L, null),
                new Profile(4L, 0L, 1L)
        );

        Map<Long, Map<Long, List<Profile>>> res = ProfileService.groupByOrgIdAndGroupId(input);

        assertEquals(1, res.size());
        assertTrue(res.containsKey(0L));
        Map<Long, List<Profile>> g0 = res.get(0L);
        assertEquals(1, g0.size());
        assertTrue(g0.containsKey(1L));
        assertEquals(Set.of(1L, 4L), toIdSet(g0.get(1L)));
    }

    @Test
    public void emptyAndNullInput() {
        assertTrue(ProfileService.groupByOrgIdAndGroupId(Collections.emptyList()).isEmpty());
        assertTrue(ProfileService.groupByOrgIdAndGroupId(null).isEmpty());
    }

    private static Set<Long> toIdSet(List<Profile> list) {
        Set<Long> ids = new HashSet<>();
        for (Profile p : list) ids.add(p.getId());
        return ids;
    }
}
