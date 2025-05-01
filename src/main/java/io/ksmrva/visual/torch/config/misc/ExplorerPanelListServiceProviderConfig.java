package io.ksmrva.visual.torch.config.misc;

import io.ksmrva.visual.torch.service.common.explorer.panel.list.entry.provider.ExplorerPanelListServiceEntryProvider;
import io.ksmrva.visual.torch.service.model.code.source.file.CodeModelSourceFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExplorerPanelListServiceProviderConfig {

    private final CodeModelSourceFileService codeModelSourceFileService;

    @Autowired
    public ExplorerPanelListServiceProviderConfig(CodeModelSourceFileService codeModelSourceFileService) {
        this.codeModelSourceFileService = codeModelSourceFileService;
    }

    @Bean(name = "codeModelFileProvider")
    public ExplorerPanelListServiceEntryProvider codeModelFileProvider() {
        return codeModelSourceFileService;
    }

}
