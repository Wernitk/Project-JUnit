package service.impl;

import entity.impl.Worker;
import service.WorkerService;

import java.util.ArrayList;
import java.util.List;

public class WorkerServiceImpl implements WorkerService {
    private final List<Worker> workers = new ArrayList<>();

    @Override
    public void hireWorker(Worker worker) {
        workers.add(worker);
    }

    @Override
    public void fireWorker(String name) {
        workers.removeIf(w -> w.getName().equals(name));
    }

    @Override
    public Worker findWorker(String name) {
        return workers.stream()
                .filter(w -> w.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Worker> getAllWorkers() {
        return new ArrayList<>(workers);
    }
}