package io.ksmrva.visual.torch.service.documentation.tool.canvas;

import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.CanvasDto;
import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.cell.CanvasCustomCellDto;
import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.cell.CanvasLinkCellDto;

import java.util.List;

public interface CanvasService {

    CanvasDto createCanvas(CanvasDto canvasDtoToCreate);

    CanvasDto getCanvas(String canvasName);

    List<String> getAllCanvasNames();

    List<CanvasDto> getAllCanvases();

    CanvasDto updateCanvas(CanvasDto canvasDtoToUpdate);

    CanvasCustomCellDto updateCanvasCustomCell(CanvasCustomCellDto canvasCustomCellDtoToUpdate);

    CanvasLinkCellDto updateCanvasLinkCell(CanvasLinkCellDto canvasLinkCellDtoToUpdate);

    void deleteCanvas(String canvasName);

}
