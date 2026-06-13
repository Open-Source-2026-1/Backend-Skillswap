package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.SessionStatus;

public record UpdateTutoringSessionStatusResource(SessionStatus status) {
}