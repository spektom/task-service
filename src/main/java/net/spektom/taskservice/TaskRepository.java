package net.spektom.taskservice;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * This class defines DB operations on Task entity
 */
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
	// Magic method name automatically generates needed query
	public List<Task> findByCompletedIsNull();
}