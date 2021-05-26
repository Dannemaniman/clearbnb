<template>
  <footer>
    <div class="footer-content">
      <ul>
        <li><a href="/">How Clearbnb works</a></li>
        <li><a href="/">Investors</a></li>
        <li><a href="/">Careers</a></li>
        <li><a href="/">Our mission</a></li>
      </ul>
      <ul>
        <li><a href="/">Diversity &#38; Belonging</a></li>
        <li><a href="/">Against Discrimination</a></li>
        <li><a href="/">Accessibility</a></li>
        <li><a href="/">Gift Cards</a></li>
      </ul>
      <ul>
        <li><a href="/">Host your Home</a></li>
        <li><a href="/">Responsible Hosting</a></li>
        <li><a href="/">Refer hosts</a></li>
        <li><a href="/">Community Center</a></li>
      </ul>
    </div>
    <hr />
    <div class="footer-company">
      <p>© 2021 Clearbnb, Inc</p>
    </div>
  </footer>
</template>

<script></script>

<style scoped>
footer {
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-color: rgb(235, 235, 235);
  width: 100vw;
}

.footer-content {
  display: flex;
  flex-direction: row;
  justify-content: center;
  padding-bottom: 1rem;
  padding-top: 1rem;
}

@media (max-width: 600px) {
  .footer-content {
    flex-direction: column;
  }
}

li {
  text-decoration: none;
  list-style: none;
  padding: 0.4rem 0;
}

ul {
  margin-right: 5rem;
}

hr {
  margin: 0 auto;
  width: 90%;
}

a {
  list-style: none;
  text-decoration: none;
  color: #4b4b4b;
  font-weight: 100;
}

a:hover,
a:active {
  color: #6b6b6b;
}

.footer-company {
  color: #4b4b4b;
  font-weight: 100;
  padding-top: 0.5rem;
}
</style>
