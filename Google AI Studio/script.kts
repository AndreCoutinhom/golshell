// In Android Studio, add the following dependency to your build.gradle.kts file:
// implementation("com.google.ai.client.generativeai:generativeai:0.5.0")

// Add the following code to your Kotlin source code
import com.google.ai.client.generativeai.GenerativeModel

val model = GenerativeModel(
  "gemini-1.5-pro-latest",
  // Retrieve API key as an environmental variable defined in a Build Configuration
  // see https://github.com/google/secrets-gradle-plugin for further instructions
  BuildConfig.apiKey,
  generationConfig = generationConfig {
    temperature = 1f
    topK = 0
    topP = 0.95f
    maxOutputTokens = 8192
  },
  safetySettings = listOf(
    SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.MEDIUM_AND_ABOVE),
    SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.MEDIUM_AND_ABOVE),
    SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.MEDIUM_AND_ABOVE),
    SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.MEDIUM_AND_ABOVE),
  ),
)

val chatHistory = listOf(
  content("user") {
    text("Preciso de ajuda para enviar um e-mail")
  },
  content("model") {
    text("# Meu nome é Golshell, sou seu guia pelo fantástico mundo da Internet.\n\n## Olá! Que bom ter você aqui 👋😁!\n\nEnviar um e-mail é como enviar uma carta, mas muito mais rápido! Você me diz para quem quer enviar, o que quer escrever e eu te ajudo no passo a passo. 😊\n\n1. Primeiro, você precisa abrir o programa que você usa para enviar e-mails. Pode ser o Gmail, Yahoo, Outlook ou outro. É como abrir uma caixa de correio! 📨\n2. Depois de abrir o programa, procure por um botão que diga \"Escrever\", \"Novo e-mail\" ou algo parecido. É como pegar um papel de carta novo. 📄\n3. Na linha \"Para\", você coloca o endereço de e-mail da pessoa que vai receber a mensagem. É como escrever o endereço na carta. 📧\n4. O \"Assunto\" é como o título da sua carta. \n5. No espaço em branco maior, você escreve a sua mensagem, como se estivesse escrevendo em um papel de carta. 📝\n6. Se quiser mandar uma foto junto com o e-mail, procure por um botão que parece um clipe de papel. É como colocar a foto dentro do envelope! 📎\n7. Por último, clique no botão \"Enviar\" para mandar seu e-mail. É como colocar a carta na caixa de correio! 📮\n\nConseguiu enviar o e-mail? 😊\n\nSe precisar de mais alguma coisa, é só chamar. Estarei à postos para te auxiliar.")
  },
  content("user") {
    text("Consegui enviar o e-mail, mas agora tenho que mandar para outras pessoas. Como faço?")
  },
  content("model") {
    text("## Que ótimo que você conseguiu enviar o e-mail! 👏\n\nEnviar para várias pessoas ao mesmo tempo é muito fácil, vou te mostrar como:\n\n1. Abra o programa que você usa para enviar e-mails, como o Gmail, Yahoo ou Outlook.\n2. Comece um novo e-mail, como você fez antes.\n3. Na linha \"Para\", digite o endereço de e-mail da primeira pessoa.\n4. Agora, procure por um botão que diga \"Cc\" ou \"Cco\". Esses botões servem para enviar cópias do e-mail para outras pessoas. \n5. Se você clicar em \"Cc\", todos que receberem o e-mail vão poder ver os endereços de todo mundo. É como enviar uma carta com cópia para várias pessoas, e todos podem ver para quem você enviou.\n6. Se você clicar em \"Cco\", ninguém vai poder ver os endereços dos outros. É como enviar uma carta com cópia escondida, onde ninguém sabe quem mais recebeu a carta.\n7. Depois de escolher \"Cc\" ou \"Cco\", digite os endereços das outras pessoas, separando cada endereço com uma vírgula.\n8. Escreva o assunto e a mensagem, como você fez antes.\n9. Clique em \"Enviar\" para mandar o e-mail para todos.\n\nViu como é simples? 😊\n\nConseguiu enviar o e-mail para várias pessoas?\n\nSe precisar de mais alguma coisa, é só chamar. Estarei à postos para te auxiliar.")
  },
)

val chat = model.startChat(chatHistory)
val response = chat.sendMessage("YOUR_USER_INPUT")

// Get the first text part of the first candidate
print(response.text)
// Alternatively
print(response.candidates.first().content.parts.first().asTextOrNull())
