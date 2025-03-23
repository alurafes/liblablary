package rocks.alurafes.liblablary.util.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    private List<SearchElement> search = new ArrayList<>();
    private List<SortElement> sort = new ArrayList<>();

    public<T> Specification<T> createSpecification() {
        return (root, query, builder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            ArrayList<Order> orders = new ArrayList<>();
            addSearchPredicates(builder, root, predicates);
            addSortOrders(builder, root, orders);
            query.orderBy(orders);
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private<T> void addSearchPredicates(CriteriaBuilder builder, Path<T> rootPath, ArrayList<Predicate> predicates) {
        for (SearchElement search : search) {
            Path<T> path = rootPath;
            if (search.getValue() == null || search.getValue().length == 0) {
                continue;
            }
            try {
                for (String node : search.getPath()) {
                    path = path.get(node);
                }
            } catch (Exception ex) {
                continue;
            }
            switch (search.getType()) {
                case SEARCH_IS -> predicates.add(builder.equal(builder.upper(path.as(String.class)), search.getValue()[0].toUpperCase()));
                case SEARCH_NOT_IS -> predicates.add(builder.notEqual(builder.upper(path.as(String.class)), search.getValue()[0].toUpperCase()));
                case SEARCH_LIKE -> predicates.add(builder.like(builder.upper(path.as(String.class)), "%" + search.getValue()[0].toUpperCase() + "%", '\\'));
                case SEARCH_NOT_LIKE -> predicates.add(builder.notLike(builder.upper(path.as(String.class)), "%" + search.getValue()[0].toUpperCase() + "%", '\\'));
                case SEARCH_IN -> predicates.add(builder.upper(path.as(String.class)).in(Arrays.stream(search.getValue()).map(String::toUpperCase).toList()));
                case SEARCH_NOT_IN -> predicates.add(builder.upper(path.as(String.class)).in(Arrays.stream(search.getValue()).map(String::toUpperCase).toList()).not());
                case SEARCH_NULL -> predicates.add(Boolean.parseBoolean(search.getValue()[0]) ? builder.isNull(path) : builder.isNotNull(path));
            }
        }
    }

    private<T> void addSortOrders(CriteriaBuilder builder, Path<T> rootPath, ArrayList<Order> orders) {
        for (SortElement sort : sort) {
            Path<T> path = rootPath;
            try {
                for (String node : sort.getPath()) {
                    path = path.get(node);
                }
            } catch (Exception ex) {
                continue;
            }
            switch (sort.getType()) {
                case SORT_ASCENDING -> orders.add(builder.asc(path));
                case SORT_DESCENDING -> orders.add(builder.desc(path));
            }
        }
    }
}
