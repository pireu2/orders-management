package org.example.Logic;

import org.example.DAO.LogDAO;
import org.example.DTO.LogDTO;
import org.example.Model.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * This class provides the logic for the Log operations.
 */
public class LogLogic {

    /**
     * Retrieves all logs.
     *
     * @return a list of all logs
     */
    public static List<LogDTO> getAllLogs(){
        LogDAO logDAO = new LogDAO();
        List<Log> logs = logDAO.findAll();
        List<LogDTO> logDTOS = new ArrayList<>();
        for(Log log : logs){
            LogDTO logDTO = new LogDTO(log.bill());
            logDTOS.add(logDTO);
        }
        return logDTOS;
    }
}
