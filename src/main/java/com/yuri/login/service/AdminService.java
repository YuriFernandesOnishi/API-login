package com.yuri.login.service;

import com.yuri.login.entity.SystemSettings;
import com.yuri.login.repository.SystemSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final SystemSettingsRepository systemSettingsRepository;

    @Autowired
    public AdminService(SystemSettingsRepository systemSettingsRepository) {
        this.systemSettingsRepository = systemSettingsRepository;
    }

    public void updateSystemSettings(SystemSettings updatedSettings) {
        // Obtém as configurações atuais (ID fixo = 1)
        SystemSettings currentSettings = systemSettingsRepository.findById(1)
                .orElseThrow(() -> new IllegalStateException("Configurações do sistema não encontradas."));

        // Atualiza os valores
        currentSettings.setMaxSpots(updatedSettings.getMaxSpots());
        currentSettings.setFirstHourPrice(updatedSettings.getFirstHourPrice());
        currentSettings.setAdditionalHourPrice(updatedSettings.getAdditionalHourPrice());

        // Salva as novas configurações no banco
        systemSettingsRepository.save(currentSettings);
    }
}