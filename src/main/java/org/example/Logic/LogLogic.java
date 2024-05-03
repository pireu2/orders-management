package org.example.Logic;

import org.example.DAO.LogDAO;
import org.example.DTO.LogDTO;
import org.example.Model.Log;

import java.util.ArrayList;
import java.util.List;

public class LogLogic {
    public static List<LogDTO> getAllLogs(){
        LogDAO logDAO = new LogDAO();
        List<Log> logs = logDAO.findAll();
        List<LogDTO> logDTOS = new ArrayList<>();
        for(Log log : logs){
            LogDTO logDTO = new LogDTO(log.getBill());
            logDTOS.add(logDTO);
        }
        return logDTOS;
    }
}
