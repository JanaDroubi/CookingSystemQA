package all;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChefTest {

    private chef testChef;

    @BeforeEach
    void setUp() {
        testChef = new chef("TestChef", "grilling", "password123", "chef");
    }

    @Test
    void testGetExpertise() {
        assertEquals("grilling", testChef.getExpertise());
    }

    @Test
    void testAssignTask() {
        testChef.assignTask("Prepare Salad");
        testChef.assignTask("Grill Steak");

        List<String> tasks = testChef.getAssignedTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains("Prepare Salad"));
        assertTrue(tasks.contains("Grill Steak"));
    }

    @Test
    void testGetTaskCount() {
        assertEquals(0, testChef.getTaskCount());

        testChef.assignTask("Bake Cake");
        assertEquals(1, testChef.getTaskCount());

        testChef.assignTask("Make Soup");
        assertEquals(2, testChef.getTaskCount());
    }

    @Test
    void testIsValid() {
        assertTrue(testChef.isValid());

        chef invalidChef = new chef(null, "grilling", "password123", "chef");
        assertFalse(invalidChef.isValid());

        chef invalidChef2 = new chef("TestChef2", null, "password123", "chef");
        assertFalse(invalidChef2.isValid());
    }
}
