from llama_cpp import Llama
from flask import Flask, jsonify, request
import sys
from flask_cors import CORS

modelfile = "ggml-model-q8_0.gguf"
contextlength = 2048
stoptoken = '<s>'

llm = Llama(
  model_path=modelfile,
  n_ctx=contextlength,
  n_gpu_layers=20,
)


app = Flask(__name__)
CORS(app, resources={r"/api/*": {"origins": "http://localhost:3010"}});

@app.route('/api/ai', methods=['get'])
def compute_promp():
    promptParameter = request.args.get('prompt')
    if(promptParameter is None):
        return jsonify('{"error" : "prompt parameter must be defined"}')
    prompt = "### USER: {} ### ASSISTANT:".format(promptParameter)

    response = llm(prompt, max_tokens=200, 
                       stop=["###"], 
                       temperature=0.1,
                       top_p=0.2,
                       top_k=10,
                       repeat_penalty=1.1)
    return jsonify(response);

if __name__ == '__main__':
    app.run(debug=False, host='127.0.0.1', port=8083)
