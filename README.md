# Application de Gestion de Résidences Étudiantes - ResidenceManager

## Description

ResidenceManager est une application web conçue pour simplifier la gestion des résidences étudiantes. Elle permet de centraliser les opérations administratives et offre une expérience utilisateur fluide pour les étudiants et l'administration.

## Fonctionnalités principales

- **Gestion des chambres** : Consultation, filtrage et réservation des chambres.
- **Gestion des étudiants** : Inscription, profils et historique des paiements.
- **Paiements** : Suivi et réalisation des paiements.
- **Maintenance** : Signalement et suivi des incidents.
- **Outils administratifs** : Gestion des étudiants, techniciens, paiements.

## Technologies utilisées

- **Backend** : Spring Boot
- **Frontend** : HTML, CSS, JavaScript
- **Base de données** : MySQL
- **Environnement de développement** : IntelliJ IDEA

## Structure de projet

Le projet suit une architecture en couches :

- **Couche Contrôleur** : Gère les requêtes HTTP.
- **Couche Service** : Logique métier.
- **Couche Repository** : Interagit avec la base de données.
- **Couche Entité** : Modélise les données.
- **Couche DTO (Data Transfer Object)** : Utilise des objets pour transmettre les données entre le backend et le frontend tout en évitant d'exposer directement les entités.
