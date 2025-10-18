import com.task1.Info;
import com.task2.Profile;
import com.task2.ProfileService;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;


public class TestTaskFirst {

    @Test
    public void profileInfoParallelAndValid() {
        Info info = new Info();

        long t0 = System.nanoTime();
        Info.ProfileInfo pi = info.getProfileInfo(42L);
        long t1 = System.nanoTime();

        long passed = TimeUnit.NANOSECONDS.toMillis(t0 - t1);

        assertNotNull(pi);
        assertNotNull(pi.userInfo);
        assertNotNull(pi.companyInfo);

        assertTrue(passed < 1000);
    }
}
