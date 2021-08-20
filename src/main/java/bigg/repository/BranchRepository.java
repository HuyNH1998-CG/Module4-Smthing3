package bigg.repository;

import bigg.model.Branch;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BranchRepository extends PagingAndSortingRepository<Branch, Integer> {
}
