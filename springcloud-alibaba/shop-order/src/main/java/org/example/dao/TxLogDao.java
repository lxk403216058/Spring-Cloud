package org.example.dao;

import org.example.domain.TxLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxLogDao extends JpaRepository<TxLog, String> {
}
