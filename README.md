<h1>Notz</h1>

Notz is a tiny application in order to test some frameworks, languages, architectural & design concepts.

<h2>Features</h2>
<ol>
<li>As an unregister user, I can sign-up in order to become a member (a.k.a "user") and to be allowed to access the full site</li>
<li>As a user, I can login</li>
<li>As a user, I can logout</li>
<li>As a user, I can edit my profile</li>
<li>As a user, I can ask a new password generation in case of forgotten password</li>
<li>As a user, I can delete my account</li>
<li>As a user, I can consult my notes</li>
<li>As a user, I can search my notes</li>
<li>As a user, I can add a note</li>
<li>As a user, I can edit a note</li>
<li>As a user, I can delete a note</li>
<li>As a user, I can share a note on Facebook</li>
<li>As a user, I can share a note on Twitter</li>
<li>As a user, I can share a note on Google+</li>
<li>As a user, I can export a note as a PDF</li>
<li>As a user, I can send a note by mail</li>
<li>As a user, I can export all my notes in an Excel file</li>
<li>As a user, I can associate Tags to my notes in order to improve sort & search</li>
</ol>

<h2>Technical constraints</h2>
I.   The application must be completely modular => separation in 4 layers : 
<ul>
<li>Notz-Core (Domain & External Interfaces),</li>
<li>Notz-Services (Persistence + Business rules),</li>
<li>Notz-WS (Web services),</li>
<li>Notz-UI (Consuming WE user interfaces made with Wicket, GWT or AngularJS)</li>
</ul>
II.  Build factory configuration (Maven + GIT) and technical design concetption are as important as implementation code

<h2>WS API</h2>
<table>
    <tr>
        <th>Route</th>
        <th>Description</th>
        <th>Method</th>
        <th>Parameters</th>
        <th>Result</th>
    </tr>
    <tr><td>/users/signup/</td><td>Register a user</td><td>PUT</td><td></td><td></td></tr>
    <tr><td>/users/login/</td><td>Register a user</td><td>POST</td><td></td><td></td></tr>
    <tr><td>/users/</td><td>Get all users</td><td>GET</td><td></td><td></td></tr>
    <tr><td>/users/{uid}</td><td>Get a user by its ID</td><td>GET</td><td></td><td></td></tr>
    <tr><td>/users/</td><td></td><td>POST</td><td>User user</td><td></td></tr>
    <tr><td>/users/</td><td>Delete a user</td><td>DELETE</td><td>String uid</td><td></td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td></tr>
</table>

-- 
Agile Spirit