from flask import Flask, render_template, request
app = Flask(__name__,
            static_url_path='',
            static_folder='static',
            template_folder='templates')

@app.route('/')
def hello():
    fruits = ['apple', 'banana', 'cherry']
    return render_template('index.html', title='Home', msg='Hello World!', formal=True, fruits=fruits)

@app.route('/user')
def user():
    userlist = {
        'Alice' : True,
        'Bob' : False,
        'Charlie' : True,
        'David' : True,
        'Eve' : True
    }
    return render_template('user.html', title='User', userlist=userlist)

@app.route('/users/<namename>')
def users(namename):
    return f"Hallo {namename}!"

@app.route('/orders/<int:order_id>')
def orders(order_id):
    return f"Order ID: {order_id}"

@app.route('/users')
def show_user_profile():
    username = request.args.get('username')
    return f"Username: {username}"

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        if username == password:
            return render_template('login.html', logged_in=True)
        else :
            return f"Benutzername: { username }"
    else:
        return render_template('login.html', logged_in=False)

if __name__ == '__main__':
    app.run(debug=True)

class User:
    def __init__(self, name, formal):
        self.name = name
        self.formal = formal
