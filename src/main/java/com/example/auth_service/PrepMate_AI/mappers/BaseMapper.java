package com.example.auth_service.PrepMate_AI.mappers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface BaseMapper<Model, Resource> {

    Model mapToModel(Resource resource);


    Resource mapToResource(Model model);

    default List<Model> mapAllToModel(List<Resource> resources) {
        return resources.stream().map(r -> mapToModel(r)).collect(Collectors.toList());
    }

    default Set<Model> mapAllToModel(Set<Resource> resources) {
        return resources.stream().map(r -> mapToModel(r)).collect(Collectors.toSet());
    }

    default List<Resource> mapAllToResource(List<Model> models) {
        return models.stream().map(m -> mapToResource(m)).collect(Collectors.toList());
    }

    default Set<Resource> mapAllToResource(Set<Model> models) {
        return models.stream().map(m -> mapToResource(m)).collect(Collectors.toSet());
    }

    default List<Model> mapAllToModel(Iterable<Resource> resources) {
        List<Model> models = new ArrayList<>();
        resources.iterator().forEachRemaining(r -> models.add(mapToModel(r)));
        return models;
    }
    default List<Resource> mapAllToResource(Iterable<Model> models) {
        List<Resource> resources = new ArrayList<>();
        models.iterator().forEachRemaining(m -> resources.add(mapToResource(m)));
        return resources;
    }

}

