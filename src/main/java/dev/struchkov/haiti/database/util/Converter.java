package dev.struchkov.haiti.database.util;

import dev.struchkov.haiti.context.enums.TypeSort;
import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.context.page.impl.SheetImpl;
import dev.struchkov.haiti.utils.Assert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

public class Converter {

    private Converter() {
        utilityClass();
    }

    public static Pageable pagination(Pagination pagination) {
        Assert.isNotNull(pagination);
        final Set<? extends dev.struchkov.haiti.context.page.Sort> sorts = pagination.getSorts();
        if (sorts != null && !sorts.isEmpty()) {
            final List<Order> orders = pagination.getSorts().stream()
                    .map(
                            sort -> new Order(
                                    TypeSort.ASC.equals(sort.getType())
                                            ? org.springframework.data.domain.Sort.Direction.ASC
                                            : org.springframework.data.domain.Sort.Direction.DESC,
                                    sort.getField()
                            )
                    ).collect(Collectors.toList());
            return PageRequest.of(
                    pagination.getPage(),
                    pagination.getSize(),
                    Sort.by(orders)
            );
        }
        return PageRequest.of(pagination.getPage(), pagination.getSize());
    }

    public static <T> Sheet<T> page(Page<T> page) {
        Assert.isNotNull(page);
        return new SheetImpl<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent()
        );
    }

}
