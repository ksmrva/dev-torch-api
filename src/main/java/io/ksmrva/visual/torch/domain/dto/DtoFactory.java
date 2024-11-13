package io.ksmrva.visual.torch.domain.dto;

import io.ksmrva.visual.torch.domain.entity.AbstractBaseEntity;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class DtoFactory {

    public static <D extends AbstractBaseDto<D, E>, E extends AbstractBaseEntity<D, E>> List<D> fromEntities(List<E> entities) {
        List<D> dtos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(entities)) {
            for (E entity : entities) {
                D dto = DtoFactory.fromEntity(entity);
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public static <D extends AbstractBaseDto<D, E>, E extends AbstractBaseEntity<D, E>> D fromEntity(E entity) {
        D dto = null;
        if (entity != null) {
            dto = entity.convertToDto();
        }
        return dto;
    }

    public static <D extends AbstractBaseDto<D, E>, E extends AbstractBaseEntity<D, E>> List<E> toEntities(List<D> dtos) {
        List<E> entities = new ArrayList<>();
        for (D dto : dtos) {
            E entity = DtoFactory.toEntity(dto);
            entities.add(entity);
        }
        return entities;
    }

    public static <D extends AbstractBaseDto<D, E>, E extends AbstractBaseEntity<D, E>> E toEntity(D dto) {
        E entity = null;
        if (dto != null) {
            entity = dto.convertToEntity();
        }
        return entity;
    }

}
