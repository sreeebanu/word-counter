function countWords() {
    const text = document.getElementById('text-input').value;

    fetch('/count', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `text=${encodeURIComponent(text)}`,
    })
    .then(response => response.json())
    .then(data => {
        const resultDiv = document.getElementById('result');
        resultDiv.innerHTML = '<h2>Word Count</h2>';
        for (const [word, count] of Object.entries(data)) {
            resultDiv.innerHTML += `<p>${word}: ${count}</p>`;
        }
    });
}
