package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands;

public record UpdateTutorCommand(Long tutorId, String fullName, String university) {}
