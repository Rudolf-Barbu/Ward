package org.bsoftware.ward.services;

import org.bsoftware.ward.components.dto.Dto;

@FunctionalInterface
public interface Service
{
    <T extends Dto> T get();
}