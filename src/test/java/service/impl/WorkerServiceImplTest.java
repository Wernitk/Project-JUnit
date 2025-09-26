package service.impl;

import entity.enums.Position;
import entity.impl.Worker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ModelsUtils;
import service.WorkerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(ModelsUtils.TEST_NAME, allWorkers.getFirst().getName());
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
}