package com.example.prj.entity;

public enum PayementStatus {
    PENDING, // Le paiement est en attente
    PAID,    // Le paiement a été effectué
    CANCELLED, // Le paiement a été annulé
    FAILED,  // Le paiement a échoué
    REFUNDED // Le paiement a été remboursé
}