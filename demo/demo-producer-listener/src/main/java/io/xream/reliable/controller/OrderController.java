package io.xream.reliable.controller;


import io.xream.reliable.bean.CatOrder;
import io.xream.reliable.repository.CatOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import x7.core.bean.condition.RefreshCondition;
import x7.core.web.ViewEntity;

@Transactional
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CatOrderRepository repository;

    @RequestMapping("/create")
    public ViewEntity create(@RequestBody CatOrder order) {

        repository.create(order);

        return ViewEntity.ok();
    }

    @RequestMapping("/confirm")
    public ViewEntity confirm(CatOrder catOrder) {

        RefreshCondition<CatOrder> catOrderRefreshCondition = new RefreshCondition<>();
        catOrderRefreshCondition.refresh("status",catOrder.getStatus());
        catOrderRefreshCondition.and().eq("id",catOrder.getId());
        repository.refresh(catOrderRefreshCondition);

        return ViewEntity.ok();
    }

    @RequestMapping("/cancel")
    public ViewEntity cancel(CatOrder catOrder) {

        RefreshCondition<CatOrder> catOrderRefreshCondition = new RefreshCondition<>();
        catOrderRefreshCondition.refresh("status",catOrder.getStatus());
        catOrderRefreshCondition.and().eq("id",catOrder.getId());
        repository.refresh(catOrderRefreshCondition);

        return ViewEntity.ok();
    }
}
