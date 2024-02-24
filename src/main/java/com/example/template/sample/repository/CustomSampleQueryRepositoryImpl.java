package com.example.template.sample.repository;

import com.example.template.sample.domain.Sample;
import com.example.template.sample.dto.request.SampleFindRequest;
import com.example.template.sample.dto.response.QSampleResponse;
import com.example.template.sample.dto.response.SampleResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.template.sample.domain.QSample.sample;

@Repository
@RequiredArgsConstructor
public class CustomSampleQueryRepositoryImpl implements CustomSampleQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<SampleResponse> findAll(SampleFindRequest sampleFindRequest, Pageable pageable) {
        List<SampleResponse> query = jpaQueryFactory
                .select(new QSampleResponse(sample))
                .from(sample)
                .where(searchCondition(sampleFindRequest))
                .orderBy(getOrderSpecifier(pageable, Sample.class))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        JPAQuery<Long> count = jpaQueryFactory
                .select(sample.count())
                .from(sample)
                .where(searchCondition(sampleFindRequest));


        return PageableExecutionUtils.getPage(query, pageable, count::fetchOne);
    }

    private BooleanBuilder searchCondition(SampleFindRequest sampleFindRequest) {
        BooleanBuilder booleanBuilder = null;

        if (sampleFindRequest != null) {
            String searchText = sampleFindRequest.getSearchText();
            if (!sampleFindRequest.getSearchType().isEmpty() && !searchText.isEmpty()) {
                switch (sampleFindRequest.getSearchType()) {
                    case "name":
                        booleanBuilder = new BooleanBuilder(sample.name.contains(searchText));
                        break;
                }
            }
        }
        return booleanBuilder;
    }


    public OrderSpecifier getOrderSpecifier(Pageable paging, Class entityClass) {
        Sort.Order[] orderList = paging.getSort().stream().toArray(Sort.Order[]::new);

        for (Sort.Order order : orderList) {
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

            PathBuilder pathBuilder = new PathBuilder(entityClass, order.getProperty());

            return new OrderSpecifier(direction, pathBuilder.get(order.getProperty()));
        }
        return null;
    }
}
