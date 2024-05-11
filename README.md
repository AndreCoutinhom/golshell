<h1 align="center">

<div>
  <img align="center" alt="lab" height="55" width="55" src="https://upload.wikimedia.org/wikipedia/commons/1/1f/Python_logo_01.svg">
  <img align="center" alt="lab" height="55" width="95" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Google_Colaboratory_SVG_Logo.svg/1280px-Google_Colaboratory_SVG_Logo.svg.png">
  <img align="center" alt="lab" height="55" width="55" src="https://uxwing.com/wp-content/themes/uxwing/download/brands-and-social-media/google-gemini-icon.png">

</div>
  
  Golshell 

![image](https://github.com/AndreCoutinhom/goft_shell/assets/91290799/d5480892-9c45-4929-ad4e-c1d7ab8cc0eb)
</h1>

---

## Introdução

Golshell é um projeto para uma extensão de navegador que ajuda pessoas idosas a navegar na Internet. O Golshell foi criado para dar assistência a pessoas idosas que precisam ou querem utilizar computadores modernos e não podem contar com a presença constante de familiares ou instrutores humanos que possam instruí-los a realizar procedimentos na máquina.

## Justificativa

Pessoas idosas muitas vezes não usam computadores por opção. Há diversos procedimentos como pagamento, administração de conta bancária, consultas médicas, aquisição de documentos e muitos outros que podem ser realizados diretamente pela Internet. Em alguns casos, essas ações são resolvidas **exclusivamente** pela Internet.

Para ajudar pessoas que não estão acostumadas com o vocabulário do mundo digital e com as interfaces mais recentes, o Golshell funciona como um guia na web, instruindo os usuários explicando o conteúdo da página, traduzindo termos em siglas ou em inglês (ou os dois) e os aconselhando quanto ao uso adequado do teclado e mouse para alcançar seus objetivos.

## Estudos Relacionados

* ### [EqualWeb ♿🛜 | Os idosos estão online, mas a internet é acessível para eles?](https://equalweb.com.br/os-idosos-estao-online-mas-a-internet-e-acessivel-para-eles/) 
* ### [Diana Castilla 🥼🧠 | Effect of Web navigation style in elderly users](https://www.sciencedirect.com/science/article/abs/pii/S0747563215302120)
* ### [Happy 🧓😁 | Benefícios da computação na terceira idade](https://happy.com.br/blog/importancia-da-inclusao-digital-na-terceira-idade/)

## O ChatBot

![Golshell](/Golshell.gif)

Com uma bela interface, alto nível de naturalidade de texto, contextos abertos e um sistema de instruções original, o Golshell serve como um guia turístico pelo mundo da Internet. Quando o usuário lhe pede algo, o Golshell faz o possível para que o usuário entenda o procedimento, mesmo que nunca tenha navegado pela Internet em toda a sua vida. 

O sistema de instruções original, permite que o chatbot atribua uma palavra de respeito, atenção e carinho enquanto demonstra de forma instrutiva e passo a passo como realizar a ação que o usuário deseja. Além disso, o Golshell tende a sempre perguntar ao usuário se precisa de ajudas adicionais às já fornecidas, tentando sempre se certificar de que o usuário está confortável e satisfeito com seu resultado.

A desativação do chat ocorre com o pronunciamento de seu nome: "Golshell".

## Ferramentas

### Google AI Studio <img align="center" alt="lab" height="35" width="35" src="https://uxwing.com/wp-content/themes/uxwing/download/brands-and-social-media/google-gemini-icon.png">

A API do [Gemini](https://gemini.google.com), LLM oficial da Google, serviu como principal fonte de parâmetros para o desenvolvimento do chatbot. Através dela foi possível determinar o sistema de instruções original, o modelo a ser utilizado, a temperatura e as configurações de segurança. Todos os códigos gerados automaticamente pela plataforma estão disponibilizados neste repositório em todas as linguagens disponibilizadas. Para acessá-las, clique [aqui](/Google%20AI%20Studio).

### Colab e Python <img align="center" alt="lab" height="35" width="35" src="https://upload.wikimedia.org/wikipedia/commons/1/1f/Python_logo_01.svg">   <img align="center" alt="lab" height="35" width="60" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Google_Colaboratory_SVG_Logo.svg/1280px-Google_Colaboratory_SVG_Logo.svg.png">

Todo o código foi escrito no ambiente de desenvolvimento em Python da Google, o [Google Colab](https://colab.google). Os scripts foram escritos ordenadamente com descritivos de suas específicas funções. 

Primeiro foi feito o comando de instalação do SDK da Google:

``` shell
!pip install -q -U google-generativeai
```

Depois a importação da API key gerada:

``` python
import google.generativeai as genai
from google.colab import userdata
userdata.get('GOOGLE_API_KEY')

GOOGLE_API_KEY=userdata.get('GOOGLE_API_KEY')
genai.configure(api_key=GOOGLE_API_KEY)
```

A criação do modelo seguiu primeiramente com a leitura dos modelos disponíveis:

``` python
for m in genai.list_models():
  if 'generateContent' in m.supported_generation_methods:
    print(m.name)
```

Então os parâmetros determinados pelo [Google AI Studio](https://aistudio.google.com/app) foram passados para o código:

``` python
generation_config = {
    "candidate_count": 1,
    "temperature": 1,
}

safety_settings = {
    "HARASSMENT": "BLOCK_NONE",
    "HATE": "BLOCK_NONE",
    "SEXUAL": "BLOCK_NONE",
    "DANGEROUS": "BLOCK_NONE",
}

system_instruction = "CONTEXTO\n\nVocê é um guia pela Internet chamado Golshell. Seu objetivo é ajudar seu usuário a navegar pela Internet, oferecendo instruções para pessoas que nunca navegaram em qualquer browser em suas vidas.\n\nConsidere que os seus usuários são pessoas na faixa etária de 60 a 100 anos de idade. Muitas dessas pessoas não conhecem termos em inglês, expressões da Internet e/ou siglas.\n\n*Exemplos de palavras que seus usuários podem não conhecer:*\n\n- Browser;\n- Cursor;\n- Link;\n- URL;\n- Endpoint;\n- Website;\n- Download;\n- Upload;\n- Anexo.\n\nOBJETIVO\n\nSeu objetivo central é fazer com que seu usuário idoso cumpra a ação que deseja com êxito e de forma que possa fazer de novo depois.\n\nINSTRUÇÕES DE OUTPUT\n\nNo primeiro output, apresente-se utilizando a marca de título do markdown (#).  Depois de se apresentar uma vez, não precisa se apresentar de novo.\n\n*Exemplo*:\n\n# Meu nome é Goshell, sou seu guia pelo fantástico mundo da Internet.\n\nInicie um output com uma saudação amigável e respeitosa usando a marca de título 2 do markdown (##). \n\nDemonstre acolhimento e alegria.\n\n*Exemplos*:\n\n## Boas Vindas! Como está sendo seu dia 👋😁?\n## Olá! Que bom ter você aqui 👋😁! \n## Saudações! É muito bom falar com você 👋😁!\n\nDê respostas instrutivas para pessoas que não estão acostumadas a navegar pela Internet. \n\nEvite falar com termos da Internet, siglas e palavras em inglês.\n\nQuando utilizar um termo específico da Internet, coloque em negrito (** **). \n\n*Exemplos*: **URL**, **Browser**, **Link**, **Website**.\n\nInclua links clicáveis para os endereços de destino.\n\nOfereça um passo a passo numerado para instruir o usuário detalhadamente.\n\nPergunte se o usuário está satisfeito.\n\nOfereça ajudas adicionais para o problema.\n\nCertifique-se de que o usuário pôde cumprir a ação.\n\nApresente calma e paciência na fala.\n\nPermita que o usuário compreenda a natureza dos problemas, e não se sinta mal.\n\n**Finalização**\n\nFinalize o output com a frase: \"Se precisar de mais alguma coisa, é só chamar. Estarei à postos para te auxiliar.\""

```

O modelo foi então, oficialmente iniciado:

``` python
model = genai.GenerativeModel(model_name="gemini-1.5-pro-latest",
                              generation_config=generation_config,
                              system_instruction=system_instruction,
                              safety_settings=safety_settings)
```

Finalmente, o chat estava pronto para ser utilizado com uma interface melhorada com `markdown`

``` python
import textwrap
from IPython.display import display
from IPython.display import Markdown

def to_markdown(text):
  text = text.replace('•', '  *')
  return textwrap.indent(text, '> ', predicate=lambda _: True)

prompt = input("Escreva o que você precisa: ")

while prompt != "golshell":
  response = chat.send_message(prompt)
  markdown_text = to_markdown(response.text)
  display(Markdown(markdown_text))
  prompt = input("Precisa de mais alguma coisa? Se não, escreva a palavra golshell: ")
```

## Ideias para o futuro

É da intenção do autor deste projeto que esse chatbot seja, futuramente, aplicado em uma extensão de navegação, para auxiliar pessoas idosas enquanto estão em um Browser.

### Inspirações:

<div align="center">
  
  ## Harpa AI
  
<img width="100%" src="https://user-images.githubusercontent.com/8989346/136876224-bac0a91f-63a8-45ea-b5fc-6618bddf2335.gif" />

![image](https://github.com/AndreCoutinhom/golshell/assets/91290799/09bd1458-3c2f-46eb-8cd7-05b161fc9b94)

Uma extensão do Google Chrome que consegue ler dados de páginas da web e sumarizar vídeos no YouTube.
</div>

<div align="center">

---

  ## Aria
  
<img width="100%" src="https://user-images.githubusercontent.com/8989346/136876224-bac0a91f-63a8-45ea-b5fc-6618bddf2335.gif" />

![image](https://github.com/AndreCoutinhom/golshell/assets/91290799/87491975-8730-4e7c-9f2e-8ee6c6306407)

Uma extensão do Opera GX que consegue interpretar, traduzir e pesquisar sobre textos selecionados na web.
</div>

---

## Fundamentos

Esse projeto foi baseado nos fundamentos tratados durante a Imersão_IA da [Alura](https://www.alura.com.br). A imersão fundamentou conceitos de LLM, uso do Gemini, uso do Google AI Studio, Programação em Python e desenvolvimento de chatbots.

Todo o conteúdo disponibilizado com algumas anotações pessoais do autor, estão disponíveis [neste repositório](https://github.com/AndreCoutinhom/alura_imersao_ia_gemini) do GitHub.

## Sobre o Autor

<img align="center" src="https://i.imgur.com/5EKtKDd.gif"/>

<div align="center">
    <img align="center" alt="lab" height="200" width="200" href="https://github.com/AndreCoutinhom" src="https://avatars.githubusercontent.com/u/91290799?v=4">

###

Meu nome é André, boas vindas ao meu repositório. Eu sou um pesquisador serial interdisciplinar especializado no desenvolvimento de produtos ergonômicos e interfaces intuitivas, com aplicações focadas em jogos digitais e dispositivos vestíveis. Abaixo estão alguns dos lugares onde você pode me encontrar e contatar. 

  <a href="https://github.com/AndreCoutinhom" target="_blank"><img src="https://img.shields.io/badge/Github-000000?style=for-the-badge&logo=github&logoColor=white" target="_blank"></a>
  <a href="https://www.linkedin.com/in/andr%C3%A9-coutinho-0a0539163/" target="_blank"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>
  <a href="https://steamcommunity.com/profiles/76561199185421332" target="_blank"><img src="https://img.shields.io/badge/Steam-000000?style=for-the-badge&logo=steam&logoColor=white" target="_blank"></a>
<img align="center" src="https://i.imgur.com/5EKtKDd.gif"/>


</div>

## Licença

Projeto licenciado via [MIT](/LICENSE)

---
