package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.adapter.ClientAdapter;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.ClientDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Client;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.service.ClientService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    private final ClientAdapter clientAdapter;

    public ClientController(final ClientService clientService, final ClientAdapter clientAdapter) {
        this.clientService = clientService;
        this.clientAdapter = clientAdapter;
    }

    @PostMapping
    public ClientDTO create(@Valid @RequestBody final ClientDTO dto) {
        final Client request = clientAdapter.adapt(dto);
        final Client persisted = clientService.create(request);
        return clientAdapter.adapt(persisted);
    }

    @GetMapping("/{id}")
    public ClientDTO find(@PathVariable final Integer id) {
        final Client persisted = clientService.find(id);
        return clientAdapter.adapt(persisted);
    }

    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable final Integer id,
                                @Valid @RequestBody final ClientDTO dto) {
        dto.setId(id);
        final Client request = clientAdapter.adapt(dto);
        final Client persisted = clientService.update(request);
        return clientAdapter.adapt(persisted);
    }

}
