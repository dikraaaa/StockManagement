# 📦 Application de Gestion de Stock

Une application web complète de gestion de stock développée en **Java avec Spring Boot** pour le backend et **Angular/React/Vue.js** (à adapter selon votre choix) pour le frontend.

## 🚀 Fonctionnalités principales

- Authentification sécurisée par **JWT**
- Gestion des **utilisateurs**
- CRUD des **articles**, **clients**, **fournisseurs**
- Gestion des **commandes clients** et **commandes fournisseurs**
- Suivi des **mouvements de stock**
- Interface utilisateur simple et intuitive

## 🔐 Authentification

Le système d’authentification utilise **JSON Web Token (JWT)** :
- L’utilisateur se connecte via un formulaire de login.
- Le backend renvoie un **token**.
- Le frontend utilise ce token pour toutes les requêtes sécurisées.

## 🖥️ Technologies utilisées

### Backend :
- Java 17
- Spring Boot
- Spring Security
- JWT
- Maven
- MySQL / PostgreSQL

### Frontend :
*(À adapter selon votre stack)*  
- Angular / React / Vue.js
- Bootstrap / Tailwind CSS
- Axios / HttpClient

## 📁 Architecture du projet


backend/
│── src/
│   ├── controller/
│   ├── dto/
│   ├── model/
│   ├── repository/
│   ├── service/
│   └── security/ (JWT, login, filter)
│── pom.xml

frontend/
│── src/
│   ├── components/
│   ├── pages/
│   ├── services/
│   └── App.vue / App.js
