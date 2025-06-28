
# 🤖 AI Chatbot – JavaFX + NLP + GPT Integration

A modern, intelligent chatbot application built in Java using JavaFX for the GUI and NLP logic for smart matching. Includes an expandable FAQ dataset with 1200+ questions across tech, finance, education, logic, and conversation. Optionally integrates OpenAI GPT for dynamic ChatGPT-style responses.

---

## 🚀 Features

- 💬 Real-time chatbot with GUI interaction
- 🎨 JavaFX-based modern interface (dark/light themes)
- 🧠 NLP logic using TF-IDF + cosine similarity
- 📂 Rule-based fallback from 1200+ trained FAQs
- 🤖 OpenAI GPT integration (optional, via API key)
- 🔍 Handles questions on AI, coding, finance, logic, general knowledge, etc.
- 📊 Clean architecture and modular code (MVC pattern)

---

## 📁 Tech Stack

- Java 17+
- JavaFX 21 (FXML, CSS)
- Maven
- JSON (json-simple)
- Optional: OpenAI GPT API

---

## 🧪 Project Structure

```
AI-Chatbot/
├── src/
│   └── main/
│       ├── java/
│       │   ├── application/
│       │   ├── controller/
│       │   ├── model/
│       │   └── utils/
│       └── resources/
│           ├── chatbot.fxml
│           ├── style-dark.css
│           ├── style-light.css
│           └── faqs.json
│          
├── pom.xml
└── README.md
```

---

## 📷 Screenshots

[![Result Screenshot](assets/result_1.png)](assets/result_1.png)
[![Result Screenshot](assets/result_2.png)](assets/result_2.png)
[![Result Screenshot](assets/result_3.png)](assets/result_3.png)
[![Result Screenshot](assets/result_4.png)](assets/result_4.png)
[![Result Screenshot](assets/result_5.png)](assets/result_5.png)

---

## ⚙️ How to Run Locally

> Requires Java 17+ and Maven installed

```bash
mvn clean compile
mvn javafx:run
```

### 🔐 Optional: GPT Mode

```bash
setx OPENAI_API_KEY "your-openai-key-here"
```

If no key is set, the chatbot falls back to NLP + FAQ logic.

---

## 📚 Topics Covered in faqs.json

- General AI & Chatbot Questions  
- Programming: Java, Python, Web, etc.  
- Finance & Trading (100 Q&A)  
- Education & Certification  
- Fun, conversational, logical Q&A  
- Math, science, UX, and error handling  
- 1200+ total question-answer pairs  

---

## 🌟 Credits

This project was built as part of a personal AI/ML initiative to combine real-time UI with NLP logic using JavaFX.
