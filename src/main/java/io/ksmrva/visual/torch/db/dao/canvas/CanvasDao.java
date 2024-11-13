package io.ksmrva.visual.torch.db.dao.canvas;

import io.ksmrva.visual.torch.domain.dto.canvas.CanvasDto;
import io.ksmrva.visual.torch.domain.dto.canvas.cell.CanvasCustomCellDto;
import io.ksmrva.visual.torch.domain.dto.canvas.cell.CanvasLinkCellDto;

import java.util.List;

public interface CanvasDao {

    /**
     * Create
     **/
    CanvasDto createCanvas(CanvasDto canvasDtoToCreate);

    /**
     * Read
     **/
    CanvasDto getCanvas(String canvasName);

    List<String> getAllCanvasNames();

    List<CanvasDto> getAllCanvases();

    /**
     * Update
     **/
    CanvasDto updateCanvas(CanvasDto canvasDtoToUpdate);

    CanvasCustomCellDto updateCanvasCustomCell(CanvasCustomCellDto customCellDtoToUpdate);

    CanvasLinkCellDto updateCanvasLinkCell(CanvasLinkCellDto linkCellDtoToUpdate);

    /**
     * Delete
     **/
    void deleteCanvas(String canvasName);

}
