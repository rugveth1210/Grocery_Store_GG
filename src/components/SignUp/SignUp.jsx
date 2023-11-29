import './SignUp.css'
export default function SignUp() {
    return (
        <form action="/your-server-side-endpoint" method="post">
        <label for="firstname">Firstname:</label>
        <input type="text" id="firstname" name="firstname" required/>
    
        <label for="lastname">Lastname:</label>
        <input type="text" id="lastname" name="lastname" required/>
    
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required/>
    
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required/>
    
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required/>
    
        <label for="mobile">Mobile Number:</label>
        <input type="text" id="mobile Number" name="mobile Number" required/>
    
        <button type="submit">Finish</button>
    </form>
    );
  }