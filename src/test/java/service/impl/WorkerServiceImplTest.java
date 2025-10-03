package service.impl;

import entity.enums.Position;
import entity.impl.Worker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import service.ModelsUtils;
import service.WorkerService;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static service.ModelsUtils.TEST_NAME;

class WorkerServiceImplTest {

    private WorkerService workerService;

    @BeforeEach
    void setUp() {
        workerService = new WorkerServiceImpl();
    }

    @Test
    @DisplayName("Should hire worker successfully")
    void hireWorker() {
        Worker worker = ModelsUtils.getWorker();

        workerService.hireWorker(worker);

        List<Worker> allWorkers = workerService.getAllWorkers();

        assertEquals(1, allWorkers.size());
        assertEquals(TEST_NAME, allWorkers.getFirst().getName());
        assertEquals(Position.SELLER, allWorkers.getFirst().getPosition());
    }

    @Test
    @DisplayName("Should fire worker by name")
    void fireWorker() {
        Worker worker = ModelsUtils.getWorker();
        workerService.hireWorker(worker);

        workerService.fireWorker(worker.getName());

        assertTrue(workerService.getAllWorkers().isEmpty());
    }

    /*
    NOTE:
        Parameterized test with Value Source, sent array[?]
        int, long, double, float, short, byte, char, String, boolean
     */
    @ParameterizedTest
    @ValueSource(strings = {"james", "Anna", "Mike", "Kolt"})
    @DisplayName("Should fire worker by name parameterized test")
    void fireWorkerParameterizedTest(String testName) {
        Worker worker = new Worker(testName, Position.SELLER);
        workerService.hireWorker(worker);

        workerService.fireWorker(worker.getName());

        assertTrue(workerService.getAllWorkers().isEmpty());
    }

    /*
    NOTE:
        Parametrized test with EnumSource
        Used for enums. You can restrict the subset of values via names or mode.
        @EnumSource(Position.class, name = {"DEVELOPER"})
     */
    @ParameterizedTest
    @EnumSource(Position.class)
    @DisplayName("Should fire worker by name parameterized test")
    void fireWorkerParameterizedTestEnums(Position position) {
        Worker worker = new Worker(TEST_NAME, position);
        workerService.hireWorker(worker);

        workerService.fireWorker(worker.getName());

        assertTrue(workerService.getAllWorkers().isEmpty());
    }

    /*
    NOTE:
         For passing multiple parameters.
         Supports types: String, int, long, double, boolean.
         Values are passed as strings separated by commas.
     */
    @ParameterizedTest
    @CsvSource({
            "James, SELLER",
//            "Mike, DEVELOPER",
            "Anna, MARKETER"
    })
    @DisplayName("Should fire worker by name parameterized test")
    void fireWorkerParameterizedTestCsvSource(String testName, Position position) {
        Worker worker = new Worker(testName, position);
        workerService.hireWorker(worker);

        workerService.fireWorker(worker.getName());

        assertTrue(workerService.getAllWorkers().isEmpty());
    }

    @Test
    @DisplayName("Should find worker by name")
    void findWorker() {
        Worker worker = ModelsUtils.getWorker();
        workerService.hireWorker(worker);

        Worker found = workerService.findWorker(worker.getName());

        assertNotNull(found);
        assertEquals(worker.getName(), found.getName());

        Worker notFound = workerService.findWorker("Ghost");
        assertNull(notFound);
    }


    @Test
    @DisplayName("Should get all hired workers")
    void getAllWorkers() {
        List<Worker> workers = ModelsUtils.getRandomList(
                () -> new Worker(ModelsUtils.getRandomElement(ModelsUtils.NAMES), Position.SELLER),
                100
        );

        workers.forEach(workerService::hireWorker);

        List<Worker> allWorkers = workerService.getAllWorkers();

        assertEquals(100, allWorkers.size());
        assertTrue(allWorkers.containsAll(workers));
    }
    /*
    NOTE:
        A very flexible option.
        Returns Stream<Arguments>, List<Arguments>, or Stream/Iterable of specific types.
         Allows you to generate combinations programmatically.
     */

    @ParameterizedTest
    @MethodSource("workerProvider")
    @DisplayName("Should get all hired workers")
    void getAllWorkersParameterizedTestMethodSource(String testName, Position position) {
        Worker worker = new Worker(testName, position);
        workerService.hireWorker(worker);

        List<Worker> allWorkers = workerService.getAllWorkers();

        assertEquals(1, allWorkers.size());
        assertFalse(allWorkers.isEmpty());
    }

    static Stream<Arguments> workerProvider() {
        return Stream.of(
                Arguments.of("James", Position.SELLER),
                Arguments.of("Anna", Position.CONSULTANT)
        );
    }

    /*
    NOTE:
        @ArgumentsSource - Connects its class, which implements the ArgumentsProvider interface.
        Used for complex data generation.
        @NullSource - adds null as an argument.
        @EmptySource - adds an empty value (“” or an empty list).
        @NullAndEmptySource - a combination of the previous two.
     */
}