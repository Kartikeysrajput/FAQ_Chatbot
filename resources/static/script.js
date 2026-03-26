const chatBox = document.getElementById("chat-box");
const userInput = document.getElementById("user-input");

function addMessage(text, sender) {
  const welcome = chatBox.querySelector(".welcome-msg");
  if (welcome) welcome.remove();

  const wrapper = document.createElement("div");

  if (sender === "bot") {
    const name = document.createElement("div");
    name.className = "bot-name";
    name.textContent = "FAQ Bot";
    wrapper.appendChild(name);
  }

  const div = document.createElement("div");
  div.className = "msg " + sender;
  div.textContent = text;
  wrapper.appendChild(div);
  chatBox.appendChild(wrapper);
  chatBox.scrollTop = chatBox.scrollHeight;
}

async function sendMessage() {
  const message = userInput.value.trim();
  if (!message) return;

  addMessage(message, "user");
  userInput.value = "";

  try {
    const res = await fetch("/api/chat", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ message }),
    });
    const data = await res.json();
    addMessage(data.reply, "bot");
  } catch (e) {
    addMessage("Error: Could not reach the server.", "bot");
  }
}

function askSuggestion(text) {
  userInput.value = text;
  sendMessage();
}
