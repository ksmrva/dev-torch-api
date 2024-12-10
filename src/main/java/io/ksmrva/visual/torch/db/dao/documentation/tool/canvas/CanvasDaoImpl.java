package io.ksmrva.visual.torch.db.dao.documentation.tool.canvas;

import io.ksmrva.visual.torch.domain.dto.DtoFactory;
import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.CanvasDto;
import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.cell.CanvasCustomCellDto;
import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.cell.CanvasLinkCellDto;
import io.ksmrva.visual.torch.domain.entity.documentation.tool.canvas.Canvas;
import io.ksmrva.visual.torch.domain.entity.documentation.tool.canvas.cell.CanvasCustomCell;
import io.ksmrva.visual.torch.domain.entity.documentation.tool.canvas.cell.CanvasLinkCell;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CanvasDaoImpl implements CanvasDao {

    private static final Logger LOGGER = LogManager.getLogger(CanvasDaoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public CanvasDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Create
     **/
    @Override
    public CanvasDto createCanvas(CanvasDto canvasDtoToCreate) {
        Assert.notNull(canvasDtoToCreate, "Was provided a null Canvas to create");
        Canvas canvas = DtoFactory.toEntity(canvasDtoToCreate);
        this.sessionFactory.getCurrentSession()
                           .persist(canvas);

        return DtoFactory.fromEntity(canvas);
    }

    /**
     * Read
     **/
    @Override
    public CanvasDto getCanvas(String canvasName) {
        Canvas canvasQueryResult = getCanvasEntityByName(canvasName);

        return DtoFactory.fromEntity(canvasQueryResult);
    }

    @Override
    public List<String> getAllCanvasNames() {
        List<String> canvasNamesQueryResult;
        try {
            canvasNamesQueryResult = this.sessionFactory.getCurrentSession()
                                                        .createSelectionQuery("SELECT name FROM Canvas", String.class)
                                                        .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Canvas Names", e);
            canvasNamesQueryResult = new ArrayList<>();
        }
        return canvasNamesQueryResult;
    }

    @Override
    public List<CanvasDto> getAllCanvases() {
        List<Canvas> canvasesQueryResult;
        try {
            canvasesQueryResult = this.sessionFactory.getCurrentSession()
                                                     .createSelectionQuery("from Canvas", Canvas.class)
                                                     .getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("Found no Canvases", e);
            canvasesQueryResult = new ArrayList<>();
        }

        return DtoFactory.fromEntities(canvasesQueryResult);
    }

    /**
     * Update
     **/
    @Override
    public CanvasDto updateCanvas(CanvasDto canvasDtoToUpdate) {
        Assert.notNull(canvasDtoToUpdate, "Was provided a null Canvas to update");
        Canvas canvas = DtoFactory.toEntity(canvasDtoToUpdate);
        this.sessionFactory.getCurrentSession()
                           .merge(canvas);

        return DtoFactory.fromEntity(canvas);
    }

    @Override
    public CanvasCustomCellDto updateCanvasCustomCell(CanvasCustomCellDto customCellDtoToUpdate) {
        Assert.notNull(customCellDtoToUpdate, "Was provided a null Canvas Custom Cell to update");
        CanvasCustomCell customCell = DtoFactory.toEntity(customCellDtoToUpdate);
        this.sessionFactory.getCurrentSession()
                           .merge(customCell);

        return DtoFactory.fromEntity(customCell);
    }

    @Override
    public CanvasLinkCellDto updateCanvasLinkCell(CanvasLinkCellDto linkCellDtoToUpdate) {
        Assert.notNull(linkCellDtoToUpdate, "Was provided a null Canvas Link Cell to update");
        CanvasLinkCell linkCell = DtoFactory.toEntity(linkCellDtoToUpdate);
        this.sessionFactory.getCurrentSession()
                           .merge(linkCell);

        return DtoFactory.fromEntity(linkCell);
    }

    /**
     * Delete
     **/
    @Override
    public void deleteCanvas(String canvasName) {
        Assert.notNull(canvasName, "Was provided a null Canvas name for deletion");

        Canvas canvasToDelete = this.getCanvasEntityByName(canvasName);
        Assert.notNull(canvasToDelete, "Could not find the Canvas with name [" + canvasName + "] for deletion");

        this.sessionFactory.getCurrentSession()
                           .remove(canvasToDelete);
    }

    /**
     * Private
     **/
    private Canvas getCanvasEntityByName(String canvasName) {
        Canvas canvasQueryResult = null;
        try {
            canvasQueryResult = this.sessionFactory.getCurrentSession()
                                                   .createSelectionQuery("from Canvas where name=:canvasName", Canvas.class)
                                                   .setParameter("canvasName", canvasName)
                                                   .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn("No result found for Canvas with name [" + canvasName + "]", e);
        } catch (NonUniqueResultException e) {
            LOGGER.warn("Found more than one result for Canvas with name [" + canvasName + "]", e);
        }
        return canvasQueryResult;
    }

}
