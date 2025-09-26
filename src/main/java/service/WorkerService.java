package service;

import entity.impl.Worker;

import java.util.List;

public interface WorkerService {
    void hireWorker(Worker worker);
    void fireWorker(String name);
    Worker findWorker(String name);
    List<Worker> getAllWorkers();
}
