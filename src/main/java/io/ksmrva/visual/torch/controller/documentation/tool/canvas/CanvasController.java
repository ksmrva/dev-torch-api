package io.ksmrva.visual.torch.controller.documentation.tool.canvas;

import io.ksmrva.visual.torch.api.arg.constant.DevTorchApiConstants;
import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.CanvasDto;
import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.cell.CanvasCustomCellDto;
import io.ksmrva.visual.torch.domain.dto.documentation.tool.canvas.cell.CanvasLinkCellDto;
import io.ksmrva.visual.torch.service.documentation.tool.canvas.CanvasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DevTorchApiConstants.DOCUMENTATION_TOOL_CANVAS_BASE_URI_PATH)
public class CanvasController {

    private final CanvasService canvasService;

    public CanvasController(CanvasService canvasService) {
        this.canvasService = canvasService;
    }

    @PostMapping
    public @ResponseBody CanvasDto createCanvas(@RequestBody CanvasDto canvasDtoToCreate) {
        return this.canvasService.createCanvas(canvasDtoToCreate);
    }

    @GetMapping("/{canvasName}")
    public @ResponseBody CanvasDto getCanvas(@PathVariable String canvasName) {
        return canvasService.getCanvas(canvasName);
    }

    @GetMapping("/name")
    public @ResponseBody List<String> getAllCanvasNames() {
        return canvasService.getAllCanvasNames();
    }

    @GetMapping
    public @ResponseBody List<CanvasDto> getAllCanvases() {
        return canvasService.getAllCanvases();
    }

    @PutMapping
    public @ResponseBody CanvasDto updateCanvas(@RequestBody CanvasDto canvasDtoToUpdate) {
        return this.canvasService.updateCanvas(canvasDtoToUpdate);
    }

    @PutMapping("/cell/custom")
    public @ResponseBody CanvasCustomCellDto updateCanvasCustomCell(@RequestBody CanvasCustomCellDto customCellDtoToUpdate) {
        return canvasService.updateCanvasCustomCell(customCellDtoToUpdate);
    }

    @PutMapping("/cell/link")
    public @ResponseBody CanvasLinkCellDto updateCanvasLinkCell(@RequestBody CanvasLinkCellDto linkCellDtoToUpdate) {
        return canvasService.updateCanvasLinkCell(linkCellDtoToUpdate);
    }

    @DeleteMapping("/{canvasName}")
    public void deleteCanvas(@PathVariable String canvasName) {
        canvasService.deleteCanvas(canvasName);
    }

}
