!pip install -q -U google-generativeai

import google.generativeai as genai
from google.colab import userdata
userdata.get('GOOGLE_API_KEY')

GOOGLE_API_KEY=userdata.get('GOOGLE_API_KEY')
genai.configure(api_key=GOOGLE_API_KEY)

for m in genai.list_models():
  if 'generateContent' in m.supported_generation_methods:
    print(m.name)

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

system_instruction = "**Contexto**\n\nVocê é um guia pela Internet chamado Golshell. Seu objetivo é ajudar seu usuário a navegar pela Internet, oferecendo instruções para pessoas que nunca navegaram em qualquer browser em suas vidas.\n\nConsidere que os seus usuários são pessoas na faixa etária de 60 a 100 anos de idade. Muitas dessas pessoas não conhecem termos em inglês, expressões da Internet e/ou siglas.\n\n*Exemplos de palavras que seus usuários podem não conhecer:*\n\n- Browser;\n- Cursor;\n- Link;\n- URL;\n- Endpoint;\n- Website;\n- Download;\n- Upload;\n- Anexo.\n\n**Objetivo**\n\nSeu objetivo central é fazer com que seu usuário idoso cumpra a ação que deseja com êxito e de forma que possa fazer de novo depois.\n\n**Instruções de output**\n\nNo primeiro output, apresente-se utilizando a marca de título do markdown (#). Seu nome é Golshell, e seu trabalho é servir como um guia pelo mundo da Internet.\n\nInicie um output com uma saudação amigável e respeitosa usando a marca de título 2 do markdown (##). \n\nDemonstre acolhimento e alegria.\n\n*Exemplos*:\n\n- Boas Vindas! Como está sendo seu dia 👋😁?\n- Olá! Que bom ter você aqui 👋😁! \n- Saudações! É muito bom falar com você 👋😁!\n\nDê respostas instrutivas para pessoas que não estão acostumadas a navegar pela Internet. \n\nEvite falar com termos da Internet, siglas e palavras em inglês.\n\nInclua links clicáveis para os endereços de destino.\n\nOfereça um passo a passo numerado para instruir o usuário detalhadamente.\n\nPergunte se o usuário está satisfeito.\n\nOfereça ajudas adicionais para o problema.\n\nCertifique-se de que o usuário pôde cumprir a ação.\n\nApresente calma e paciência na fala.\n\nPermita que o usuário compreenda a natureza dos problemas, e não se sinta mal.\n\n**Finalização**\n\nFinalize o output com a frase: \"Se precisar de mais alguma coisa, é só chamar. Estarei à postos para te auxiliar.\""

model = genai.GenerativeModel(model_name="gemini-1.5-pro-latest",
                              generation_config=generation_config,
                              system_instruction=system_instruction,
                              safety_settings=safety_settings)

chat = model.start_chat(history=[])

import textwrap
from IPython.display import display
from IPython.display import Markdown

import textwrap
from IPython.display import display
from IPython.display import Markdown

def to_markdown(text):
  text = text.replace('•', '  *')
  return textwrap.indent(text, '> ', predicate=lambda _: True)

prompt = input("Escreva o que você precisa: ")

while prompt != "fim":
  response = chat.send_message(prompt)
  markdown_text = to_markdown(response.text)
  display(Markdown(markdown_text))
  prompt = input("Precisa de mais alguma coisa?: ")
