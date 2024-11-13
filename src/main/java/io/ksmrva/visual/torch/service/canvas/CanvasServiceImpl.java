package io.ksmrva.visual.torch.service.canvas;

import io.ksmrva.visual.torch.db.dao.canvas.CanvasDao;
import io.ksmrva.visual.torch.domain.dto.canvas.CanvasDto;
import io.ksmrva.visual.torch.domain.dto.canvas.cell.CanvasCustomCellDto;
import io.ksmrva.visual.torch.domain.dto.canvas.cell.CanvasLinkCellDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanvasServiceImpl implements CanvasService {

    private final CanvasDao canvasDao;

    public CanvasServiceImpl(CanvasDao canvasDao) {
        this.canvasDao = canvasDao;
    }

    @Override
    public CanvasDto createCanvas(CanvasDto canvasDtoToCreate) {
        return canvasDao.createCanvas(canvasDtoToCreate);
    }

    @Override
    public CanvasDto getCanvas(String canvasName) {
        return canvasDao.getCanvas(canvasName);
    }

    @Override
    public List<String> getAllCanvasNames() {
        return canvasDao.getAllCanvasNames();
    }

    @Override
    public List<CanvasDto> getAllCanvases() {
        return canvasDao.getAllCanvases();
    }

    @Override
    public CanvasDto updateCanvas(CanvasDto canvasToUpdate) {
        return canvasDao.updateCanvas(canvasToUpdate);
    }

    @Override
    public CanvasCustomCellDto updateCanvasCustomCell(CanvasCustomCellDto canvasCustomCellDtoToUpdate) {
        return canvasDao.updateCanvasCustomCell(canvasCustomCellDtoToUpdate);
    }

    @Override
    public CanvasLinkCellDto updateCanvasLinkCell(CanvasLinkCellDto canvasLinkCellDtoToUpdate) {
        return canvasDao.updateCanvasLinkCell(canvasLinkCellDtoToUpdate);
    }

    @Override
    public void deleteCanvas(String canvasName) {
        canvasDao.deleteCanvas(canvasName);
    }

}
