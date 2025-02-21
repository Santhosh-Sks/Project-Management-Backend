package com.project_manage.projectmanagementsystem.service;

import com.project_manage.projectmanagementsystem.model.PlanType;
import com.project_manage.projectmanagementsystem.model.Subscription;
import com.project_manage.projectmanagementsystem.model.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUserSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType)throws Exception;

    boolean isValid(Subscription subscription);


}
