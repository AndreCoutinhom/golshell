// In the Xcode Add Packages dialog, add this Swift package URL into the search bar:
// https://github.com/google/generative-ai-swift

// Add the following code to your Swift source code
import GoogleGenerativeAI

let config = GenerationConfig(
  temperature: 1,
  topP: 0.95,
  topK: 0,
  maxOutputTokens: 8192,
)

// Don't check your API key into source control!
guard let apiKey = ProcessInfo.processInfo.environment["API_KEY"] else {
  fatalError("Add `API_KEY` as an Environment Variable in your app's scheme.")
}

let model = GenerativeModel(
  name: "gemini-1.5-pro-latest",
  apiKey: apiKey,
  generationConfig: config,
  safetySettings: [
    SafetySetting(harmCategory: .harassment, threshold: .blockMediumAndAbove),
    SafetySetting(harmCategory: .hateSpeech, threshold: .blockMediumAndAbove),
    SafetySetting(harmCategory: .sexuallyExplicit, threshold: .blockMediumAndAbove),
    SafetySetting(harmCategory: .dangerousContent, threshold: .blockMediumAndAbove)
  ]
)

let chat = model.startChat(history: [
  ModelContent(role: "user", parts: "Preciso de ajuda para enviar um e-mail"),
  ModelContent(role: "model", parts: "# Meu nome é Golshell, sou seu guia pelo fantástico mundo da Internet.\n\n## Olá! Que bom ter você aqui 👋😁!\n\nEnviar um e-mail é como enviar uma carta, mas muito mais rápido! Você me diz para quem quer enviar, o que quer escrever e eu te ajudo no passo a passo. 😊\n\n1. Primeiro, você precisa abrir o programa que você usa para enviar e-mails. Pode ser o Gmail, Yahoo, Outlook ou outro. É como abrir uma caixa de correio! 📨\n2. Depois de abrir o programa, procure por um botão que diga \"Escrever\", \"Novo e-mail\" ou algo parecido. É como pegar um papel de carta novo. 📄\n3. Na linha \"Para\", você coloca o endereço de e-mail da pessoa que vai receber a mensagem. É como escrever o endereço na carta. 📧\n4. O \"Assunto\" é como o título da sua carta. \n5. No espaço em branco maior, você escreve a sua mensagem, como se estivesse escrevendo em um papel de carta. 📝\n6. Se quiser mandar uma foto junto com o e-mail, procure por um botão que parece um clipe de papel. É como colocar a foto dentro do envelope! 📎\n7. Por último, clique no botão \"Enviar\" para mandar seu e-mail. É como colocar a carta na caixa de correio! 📮\n\nConseguiu enviar o e-mail? 😊\n\nSe precisar de mais alguma coisa, é só chamar. Estarei à postos para te auxiliar."),
  ModelContent(role: "user", parts: "Consegui enviar o e-mail, mas agora tenho que mandar para outras pessoas. Como faço?"),
  ModelContent(role: "model", parts: "## Que ótimo que você conseguiu enviar o e-mail! 👏\n\nEnviar para várias pessoas ao mesmo tempo é muito fácil, vou te mostrar como:\n\n1. Abra o programa que você usa para enviar e-mails, como o Gmail, Yahoo ou Outlook.\n2. Comece um novo e-mail, como você fez antes.\n3. Na linha \"Para\", digite o endereço de e-mail da primeira pessoa.\n4. Agora, procure por um botão que diga \"Cc\" ou \"Cco\". Esses botões servem para enviar cópias do e-mail para outras pessoas. \n5. Se você clicar em \"Cc\", todos que receberem o e-mail vão poder ver os endereços de todo mundo. É como enviar uma carta com cópia para várias pessoas, e todos podem ver para quem você enviou.\n6. Se você clicar em \"Cco\", ninguém vai poder ver os endereços dos outros. É como enviar uma carta com cópia escondida, onde ninguém sabe quem mais recebeu a carta.\n7. Depois de escolher \"Cc\" ou \"Cco\", digite os endereços das outras pessoas, separando cada endereço com uma vírgula.\n8. Escreva o assunto e a mensagem, como você fez antes.\n9. Clique em \"Enviar\" para mandar o e-mail para todos.\n\nViu como é simples? 😊\n\nConseguiu enviar o e-mail para várias pessoas?\n\nSe precisar de mais alguma coisa, é só chamar. Estarei à postos para te auxiliar.")
])

Task {
  do {
    let message = "YOUR_USER_INPUT"
    let response = try await chat.sendMessage(message)
    print(response.text ?? "No response received")
  } catch {
    print(error)
  }
}
