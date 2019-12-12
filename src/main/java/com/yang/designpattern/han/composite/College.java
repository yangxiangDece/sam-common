package com.yang.designpattern.han.composite;

import java.util.ArrayList;
import java.util.List;

// Composite
public class College extends OrganizationComponent {

    private List<OrganizationComponent> components = new ArrayList<>();

    public College(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        components.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        components.remove(organizationComponent);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDesc() {
        return super.getDesc();
    }

    @Override
    protected void print() {
        System.out.println("------------------------" + getName() + "----------------------");
        components.forEach(OrganizationComponent::print);
    }
}
